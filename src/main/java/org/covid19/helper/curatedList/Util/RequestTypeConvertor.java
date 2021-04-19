package org.covid19.helper.curatedList.Util;

import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.beans.PropertyEditorSupport;

public class RequestTypeConvertor extends PropertyEditorSupport {

    public void setAsText(final String text){
        setValue(ApplicationConstants.RequestType.valueOf(text));
    }
}
