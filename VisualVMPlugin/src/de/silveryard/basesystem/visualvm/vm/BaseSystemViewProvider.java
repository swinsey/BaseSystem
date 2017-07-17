/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.silveryard.basesystem.visualvm.vm;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.application.type.ApplicationTypeFactory;
import com.sun.tools.visualvm.core.ui.DataSourceView;
import com.sun.tools.visualvm.core.ui.DataSourceViewProvider;
import com.sun.tools.visualvm.core.ui.DataSourceViewsManager;

import java.io.IOException;

/**
 *
 * @author Sebif
 */
public class BaseSystemViewProvider extends DataSourceViewProvider<Application> {
       private static DataSourceViewProvider<Application> instance =  new BaseSystemViewProvider();

    @Override
    public boolean supportsViewFor(Application application) {
         if (ApplicationTypeFactory.getApplicationTypeFor(application) instanceof BaseSystemApplicationType) {
            return true;
        }
        return false;
    }

    @Override
    public synchronized DataSourceView createView(final Application application) {
        try {
            return new BaseSystemView(application);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static void initialize() {
        DataSourceViewsManager.sharedInstance().addViewProvider(instance, Application.class);
    }
    
    public static void unregister() {
        DataSourceViewsManager.sharedInstance().removeViewProvider(instance);
    }
}
