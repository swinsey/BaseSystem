/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.silveryard.basesystem.visualvm.vm;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.core.datasource.DataSource;
import com.sun.tools.visualvm.core.ui.DataSourceView;
import com.sun.tools.visualvm.core.ui.components.DataViewComponent;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.openide.util.Utilities;

/**
 *
 * @author Sebif
 */
public class BaseSystemView extends DataSourceView {
    private DataViewComponent dvc;
    //Reusing an image from the sources:
    private static final String IMAGE_PATH = "/com/sun/tools/visualvm/coredump/resources/coredump.png"; // NOI18N

    public BaseSystemView(Application application) throws IOException {
        super(application, "Base System", new ImageIcon(Utilities.loadImage(IMAGE_PATH, true)).getImage(), 60, false);
    }

    @Override
    protected DataViewComponent createComponent() {
        JEditorPane generalDataArea = new JEditorPane();
        generalDataArea.setBorder(BorderFactory.createEmptyBorder(14, 8, 14, 8));
        
        JPanel panel = new JPanel();
        
        DataViewComponent.MasterView masterView = new DataViewComponent.MasterView("Base System Overview", null, generalDataArea);
        DataViewComponent.MasterViewConfiguration masterViewConfiguration = new DataViewComponent.MasterViewConfiguration(false);

        dvc = new DataViewComponent(masterView, masterViewConfiguration);

        dvc.configureDetailsArea(new DataViewComponent.DetailsAreaConfiguration("Detail 1", true), DataViewComponent.TOP_LEFT);
        dvc.configureDetailsArea(new DataViewComponent.DetailsAreaConfiguration("Detail the second", true), DataViewComponent.TOP_RIGHT);
        dvc.configureDetailsArea(new DataViewComponent.DetailsAreaConfiguration("D 3 +4", true), DataViewComponent.BOTTOM_RIGHT);

        dvc.addDetailsView(new DataViewComponent.DetailsView("Detail 1", null, 10, panel, null), DataViewComponent.TOP_LEFT);
        dvc.addDetailsView(new DataViewComponent.DetailsView("Detail1 2", null, 10, panel, null), DataViewComponent.TOP_RIGHT);
        dvc.addDetailsView(new DataViewComponent.DetailsView("Detail11 3", null, 10, panel, null), DataViewComponent.BOTTOM_RIGHT);
        dvc.addDetailsView(new DataViewComponent.DetailsView("Detail11 4", null, 10, panel, null), DataViewComponent.BOTTOM_RIGHT);

        return dvc;
    }
}
