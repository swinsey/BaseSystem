/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.silveryard.basesystem.visualvm.vm;

import com.sun.tools.visualvm.application.type.ApplicationType;
import java.awt.Image;
import org.openide.util.Utilities;

/**
 *
 * @author Sebif
 */
public class BaseSystemApplicationType extends ApplicationType {
    protected final int appPid;
    protected final String name;
    
    public BaseSystemApplicationType(int pid, String name){
        appPid = pid;
        this.name = name;
    }
    
    @Override
    public String getName() {
        return "BS App: " + name;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getDescription() {
        return "Base system Application";
    }

    @Override
    public Image getIcon() {
        return Utilities.loadImage("com/sun/tools/visualvm/core/ui/resources/snapshot.png", true);
    }
    
}
