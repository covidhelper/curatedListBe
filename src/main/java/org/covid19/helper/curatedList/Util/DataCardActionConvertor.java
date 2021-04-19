package org.covid19.helper.curatedList.Util;

import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.beans.PropertyEditorSupport;

public class DataCardActionConvertor extends PropertyEditorSupport {

    public void setAsText(final String text){
        setValue(ApplicationConstants.DataCardAction.valueOf(text));
    }
}
