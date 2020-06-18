package com.utn.TPFinal.model.projections;

import java.util.Date;

public interface InfraResponse {
   Integer getCallId();
   String getMessage();
   Date getCreatedOn();

   void setCallId(Integer callId);
   void setMessage(String message);
   void setCreatedOn(Date date);

}
