/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.silveryard.basesystem.visualvm.vm;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.application.jvm.Jvm;
import com.sun.tools.visualvm.application.type.ApplicationType;
import com.sun.tools.visualvm.application.type.MainClassApplicationTypeFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sebif
 */
public class BaseSystemApplicationTypeFactory extends MainClassApplicationTypeFactory {
      @Override
    public ApplicationType createApplicationTypeFor(Application app, Jvm jvm, String mainClass) {

        String mainArgs = jvm.getMainArgs();
        
        if(mainArgs == null){
            return null;
        }
        
        mainArgs = mainArgs.replace('\\', '/');
        
        if(!mainArgs.contains("/sys/bin")){
            return null;
        }
        
        Pattern p = Pattern.compile("app/([.\\w]+?)/files", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mainArgs);
        if(m.find()){
            String s = m.group(1);
            return new BaseSystemApplicationType(app.getPid(), s);
        }else{
            return null;
        }
    }
}
