package de.silveryard.basesystem.gradleplugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

/**
 * Created by silveryard on 08.05.17.
 */
public class RunCmakeTask extends DefaultTask {
    @InputDirectory
    public File getSourceDirectory(){
        return getProject().file("src");
    }
    @InputFile
    public File getCmakeLists(){
        return getProject().file("CMakeLists.txt");
    }

    @OutputDirectory
    public File getCmakeDirectory(){
        return getProject().file("build/cmake");
    }
    @OutputDirectory
    public File getBinDirectory(){
        return getProject().file("build/bin");
    }

    @TaskAction
    public void invoke() throws Exception {
        Helper.execute("cmake ../.. -DCMAKE_RUNTIME_OUTPUT_DIRECTORY=" + getBinDirectory().getAbsolutePath() + " -DCMAKE_LIBRARY_OUTPUT_DIRECTORY=" + getBinDirectory().getAbsolutePath(), getCmakeDirectory());
        Helper.execute("cmake --build . --target all", getCmakeDirectory());
    }
}
