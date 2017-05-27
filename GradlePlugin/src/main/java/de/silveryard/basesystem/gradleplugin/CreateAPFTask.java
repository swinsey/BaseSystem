package de.silveryard.basesystem.gradleplugin;

import de.silveryard.apfcreator.Creator;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

/**
 * TODO: Make incremental
 * Created by silveryard on 10.05.17.
 */
public class CreateAPFTask extends DefaultTask {
    @OutputFile
    public File getOutputFile(){
        Project project = getProject();

        String name = project.getName().replace('.', '_');
        String group = ("" + project.getGroup()).replace('.', '_');
        String version = ("" + project.getVersion()).replace('.', '_');

        return project.file("build/apf/" + group + "_" + name + "_" + version + ".apf");
    }

    @InputFile
    private File getConfigFile(){
        return getProject().file("apf.conf");
    }
    @InputDirectory
    private File getSourceDirectory(){
        return getProject().file("src");
    }

    @TaskAction
    private void invoke(){
        Creator.create(getConfigFile().toPath(), getOutputFile().toPath());
    }
}
