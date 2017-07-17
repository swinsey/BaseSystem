package de.silveryard.basesystem.visualvm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.application.type.ApplicationTypeFactory;
import com.sun.tools.visualvm.core.datasource.DataSource;
import com.sun.tools.visualvm.core.datasource.descriptor.DataSourceDescriptorFactory;
import com.sun.tools.visualvm.core.datasupport.DataChangeEvent;
import com.sun.tools.visualvm.core.datasupport.DataChangeListener;
import com.sun.tools.visualvm.host.Host;
import de.silveryard.basesystem.visualvm.vm.BaseSystemApplicationTypeFactory;
import de.silveryard.basesystem.visualvm.vm.BaseSystemViewProvider;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {
    private static BaseSystemApplicationTypeFactory factory = new BaseSystemApplicationTypeFactory();
    
    @Override
    public void restored() {
        BaseSystemViewProvider.initialize();
        ApplicationTypeFactory.getDefault().registerProvider(factory);
        
    }
    @Override
    public void uninstalled() {
        BaseSystemViewProvider.unregister();
        ApplicationTypeFactory.getDefault().unregisterProvider(factory);
    }
}
