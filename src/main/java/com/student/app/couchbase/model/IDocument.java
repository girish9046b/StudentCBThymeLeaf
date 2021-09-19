package com.student.app.couchbase.model;

/**
 * interface for all our Document objects
 * @author blitzkriegdevelopment <blitzkriegdevelopment.com>
 */
public interface IDocument {
	public int getId();
	public String getName();
	public String getJsonType();
	public String getValue();
	public void setId(int $id);
	public void setName(String $name);
	public void setJsonType(String $jsonType);
}
