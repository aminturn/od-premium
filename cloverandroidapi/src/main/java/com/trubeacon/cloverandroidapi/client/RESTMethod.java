package com.trubeacon.cloverandroidapi.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trubeacon.cloverandroidapi.client.RESTService.RESTServiceCallback;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.filter.Filter;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpStatus;
import com.trubeacon.cloverandroidapi.client.json.JacksonObjectMapper;
import com.trubeacon.cloverandroidapi.client.pagination.Pagination;
import com.trubeacon.cloverandroidapi.client.param.Header;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.PathParam;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;
import com.trubeacon.cloverandroidapi.client.sort.Sort;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class RESTMethod<T> {

	protected RESTService restService = RESTServiceResolverHolder.getRESTService();
	protected TypeReference<T> dataType;
	protected String url;
	protected HttpMethod method;
	protected Object entity;
		
	protected String token;
	//protected Pagination pagination;
	protected Set<PathParam> pathParams = new HashSet<PathParam>();
	protected Set<QueryParam> queryParams = new HashSet<QueryParam>();
	protected Set<Header> headers = new HashSet<Header>();
	
	public interface Callback<T> {
		public void onReceiveResult(T result);
		public void onReceiveError(Error error);
	}
	
	public RESTMethod(String token, TypeReference<T> dataType, String url, HttpMethod method, Object... params) {
		this.token = token;
		this.dataType = dataType;
		this.url = url;
		this.method = method;
		if (params != null) {
			for (Object object : params) {
				if (object instanceof Pagination) {
					setPagination((Pagination) object);
				}
				else if (object instanceof Filter) {
					addFilter((Filter) object);
				}
				else if (object instanceof Sort) {
					addSorting((Sort) object);
				}
			}
		}
	}	

	protected void setEntity(Object entity) {
		this.entity = entity;
	}
	protected void addPathParam(PathParam param) {
		pathParams.add(param);
	}
	protected void setPathParams(Set<PathParam> params) {
		this.pathParams = params;
	}	
	protected void addFilter(Filter filter) {
		if (filter != null && filter.getLval() != null) {
			if (filterables() == null) {
				queryParams.add(filter.toQueryParam());
			}
			else if (filterables().contains(filter.getLval().toString())) {
				queryParams.add(filter.toQueryParam());
			}
		}
	}
	
	protected void addSorting(Sort sort) {
		if (sort != null) {
			queryParams.add(sort.toQueryParam());
		}
	}
	protected void setPagination(Pagination pagination) {
		if (pagination != null) {
			addQueryParam(pagination.toOffsetQueryParam());
			addQueryParam(pagination.toLimitQueryParam());
		}
	}
	protected void addQueryParam(QueryParam queryParam) {
		queryParams.add(queryParam);
	}
	protected void addQueryParams(Collection<QueryParam> queryParams) {
		queryParams = new HashSet<QueryParam>(queryParams);
	}	
	protected void setHeader(Header header) {
		headers.add(header);
	}
	protected void setHeaders(Collection<Header> headers) {
		headers = new HashSet<Header>(headers);
	}
	
	protected T execute() throws RESTException {
		
		if (restService == null) throw new IllegalStateException("REST Service must be specified");
		if (method == null) throw new IllegalStateException("HTTP method must be specified");

		// use reflection to check if there's a field EXPANDABLE_FIELDS and add in the expand query params if it exists
		if (method == HttpMethod.GET && expandables() != null && !expandables.isEmpty()) {
			String queryString = "";
			String delimiter = "";
			for (String expandable : expandables()) {
				queryString += delimiter + expandable;
				delimiter = ",";
			}
			if (!queryString.isEmpty()) {
				queryParams.add(Param.query("expand", queryString));
			}						
		}
		
		RESTHttpResponse response = null;
		String entityString;
        try {
	        entityString = JacksonObjectMapper.getObjectMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
	        throw new IllegalStateException("Failed to write object as JSON");
        }

		try {
			
			switch(method) {
			case GET:
				response = restService.get(token, url, pathParams, queryParams, headers);
				break;
			case POST:
				response = restService.post(token, url, pathParams, queryParams, entityString, headers);
				break;
			case PUT:
				response = restService.put(token, url, pathParams, queryParams, entityString, headers);
				break;
			case DELETE:
				response = restService.delete(token, url, pathParams, queryParams, headers);
				break;
			default:
				throw new IllegalStateException("Invalid request type specified");
			}	

		} catch (Exception ex) {
			throw new RESTException("Unable to connect to server", ex, com.trubeacon.cloverandroidapi.client.error.Error.error("Unable to connect to server"));
		}

		return parseResponse(response);
	}
	
	protected void execute(final Callback<T> callback) {
		
		if (restService == null) throw new IllegalStateException("REST Service must be specified");
		if (method == null) throw new IllegalStateException("HTTP method must be specified");
		
		// use reflection to check if there's a field EXPANDABLE_FIELDS and add in the expand query params if it exists
		if (method == HttpMethod.GET && expandables() != null && !expandables.isEmpty()) {
			String queryString = "";
			String delimiter = "";
			for (String expandable : expandables()) {
				queryString += delimiter + expandable;
				delimiter = ",";
			}
			if (!queryString.isEmpty()) {
				queryParams.add(Param.query("expand", queryString));
			}						
		}
		
		String entityString;
        try {
	        entityString = JacksonObjectMapper.getObjectMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
	        throw new IllegalStateException("Failed to write object as JSON");
        }
		        
		try {
			
			switch(method) {
			case GET:
				restService.get(token, url, pathParams, queryParams, headers, new RESTServiceCallback() {

					public void onResponse(RESTHttpResponse response) {
						if (callback != null) {
							try {
								callback.onReceiveResult(parseResponse(response));
							} catch (RESTException ex) {
								callback.onReceiveError(ex.getError());
							}						
						}						
                    }
					
				});
				break;
			case POST:
				restService.post(token, url, pathParams, queryParams, entityString, headers, new RESTServiceCallback() {

					public void onResponse(RESTHttpResponse response) {
						if (callback != null) {
							try {
								callback.onReceiveResult(parseResponse(response));
							} catch (RESTException ex) {
								callback.onReceiveError(ex.getError());
							}						
						}						
                    }
					
				});
				break;
			case PUT:
				restService.put(token, url, pathParams, queryParams, entityString, headers, new RESTServiceCallback() {

					public void onResponse(RESTHttpResponse response) {
						if (callback != null) {
							try {
								callback.onReceiveResult(parseResponse(response));
							} catch (RESTException ex) {
								callback.onReceiveError(ex.getError());
							}						
						}						
                    }
					
				});
				break;
			case DELETE:
				restService.delete(token, url, pathParams, queryParams, headers, new RESTServiceCallback() {

					public void onResponse(RESTHttpResponse response) {
						if (callback != null) {
							try {
								callback.onReceiveResult(parseResponse(response));
							} catch (RESTException ex) {
								callback.onReceiveError(ex.getError());
							}						
						}						
                    }
					
				});
				break;
			default:
				throw new IllegalStateException("Invalid request type specified");
			}	

		} catch (Exception ex) {
        	
        	if (callback != null) {
        		callback.onReceiveError(Error.error("Unable to connect to server", ex));
        	}
        	
		}
		
	}

	/**
	 * @param response
	 * @return
	 * @throws RESTException
	 */
	private T parseResponse(RESTHttpResponse response) throws RESTException {
		
		T object = null;
		if (response == null) throw new IllegalStateException("Received invalid response");
		
		int status = response.getStatusCode();
		
		if ((status / 100) == (HttpStatus.OK.value() / 100)) {
			
			String responseBody;
	        try {
		        responseBody = response.getResponseBody();
	        } catch (Exception e) {
	        	throw new RESTException("Failed to read response body", e, Error.error("Failed to read response body", e));
	        }
	        
	        object = parseObject(responseBody);

		}
		else {
			throw new RESTException("Received bad response", parseError(response.getResponseBody()));
		}
		
		return object;
	}
	
	protected T parseObject(String responseBody) throws RESTException {
		ObjectMapper omapper = JacksonObjectMapper.getObjectMapper();
		T object = null;
		try {
			object = omapper.readValue(responseBody, dataType);
		} catch (Exception ex) {
			throw new RESTException("Failed to parse object from response", ex, Error.error("Failed to parse object from response", ex));
		}
		return object;
	}
	
	protected Error parseError(String errorString) throws RESTException {
		Error error = null;
		ObjectMapper omapper = JacksonObjectMapper.getObjectMapper();
		try {
			error = omapper.readValue(errorString, new TypeReference<Error>(){});
		} catch (Exception ex) {
			throw new RESTException("Failed to parse error from response", ex, Error.error("Failed to parse error from response", ex));
		}
		return error;
	}
	
	private Class<?> targetClazz() {
		// FIXME: this seems prone to breakage
		Class<?> targetClazz = null;
		try {
			Type targetType = dataType.getType();
			if (targetType instanceof ParameterizedType) {
				targetType = ((ParameterizedType) targetType).getActualTypeArguments()[0];
			}
			targetClazz = (Class<?>) targetType;
		} catch (Exception ex) {}
		return targetClazz;
	}
	
	private List<String> filterables = null;
	private List<String> filterables() {
		if (filterables == null) {
			try {
				Class<?> targetClazz = targetClazz();
				Field filterableFields = targetClazz.getField("FILTERABLE_FIELDS");
				if (filterableFields != null) {
					filterables = Arrays.asList((String[]) filterableFields.get(null));
				}					
			} catch (Exception ex) {}
		}
		return filterables;
	}
	
	private List<String> expandables = null;
	private List<String> expandables() {
		if (expandables == null) {
			try {
				Class<?> targetClazz = targetClazz();
				Field expandableFields = targetClazz.getField("EXPANDABLE_FIELDS");
				if (expandableFields != null) {
					expandables = Arrays.asList((String[]) expandableFields.get(null));
				}					
			} catch (Exception ex) {}
		}
		return expandables;
	}
	
}
