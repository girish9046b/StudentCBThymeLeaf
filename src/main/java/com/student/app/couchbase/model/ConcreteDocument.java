package com.student.app.couchbase.model;

import java.io.Serializable;
import java.util.Date;



/**
 * Purpose : main mold for any main Document created
 * @param editable  - used for admin tables deciding wether to display inputTextor outputText.
 * @param addto  - used for admin tables deciding weather to create a newdocument or update an existing one.
 * @author  blitzkriegdevelopment <blitzkriegdevelopment.com>
 */
public class ConcreteDocument implements Serializable{//extends GsonConvertable {

          private String jsonType;
          private boolean editable;
          private boolean addto = true;
          private String prefix;
          private String lastEdited =""+new Date();
          private String lastDiscontinued;
          private String addedBy;
          private String createdDoc;
          private String createdDate;
          private boolean discontinued;

        

          public String getJsonType() {
                    return jsonType;
          }

          public void setJsonType(String $jsonType) {
                    jsonType = $jsonType;
          }

          public boolean getEditable() {
                    return editable;
          }

          public void setEditable(boolean editable) {
                    this.editable = editable;
          }

          public boolean isAddTo() {
                    return addto;
          }

          public void setAddTo(boolean addto) {
                    this.addto = addto;
          }

          public String getPrefix() {
                    return prefix;
          }

          public void setPrefix(String prefix) {
                    this.prefix = prefix;
          }

          public boolean isDiscontinued() {
                    return discontinued;
          }

          public void setDiscontinued(boolean discontinued) {
                    this.discontinued = discontinued;
          }


          public String getLastEdited() {
                    return lastEdited;
          }

          public void setLastEdited(String user, String date) {
                    this.lastEdited = "Last edited by: " + user + " " + date;
          }

          public String getLastDiscontinued() {
                    return lastDiscontinued;
          }

          public void setLastDiscontinued(String user, String date) {
                    this.lastDiscontinued = "Last discontinued by: " + user + " " + date;
          }

          public void setCreatedDoc(String user, String date) {
                    this.createdDoc = "Created by: " + user + " " + date;
          }

          public String getCreatedDoc() {
                    return this.createdDoc;
          }

          public String getAddedBy() {
                    return addedBy;
          }

          public void setAddedBy(String user, String date) {
                    this.addedBy = "Added by: " + user + " " + date;
          }

          public String getCreatedDate() {
                    return createdDate;
          }

          public void setCreatedDate(String createdDate) {
                    this.createdDate = createdDate;
          }
          
          
}
