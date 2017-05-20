package de.silveryard.basesystem.gradleplugin;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
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
        String commandLine = "cmake ../.. -DCMAKE_RUNTIME_OUTPUT_DIRECTORY=" + getBinDirectory().getAbsolutePath() + " -DCMAKE_LIBRARY_OUTPUT_DIRECTORY=" + getBinDirectory().getAbsolutePath();

        if(SystemUtils.IS_OS_WINDOWS){
            commandLine += " -G \"Visual Studio 15 2017 Win64\"";
        }

        Helper.execute(commandLine, getCmakeDirectory());

        commandLine = "cmake --build . --target ";

        if(SystemUtils.IS_OS_WINDOWS){
            commandLine += "ALL_BUILD";
        }else{
            commandLine += "all";
        }

        Helper.execute(commandLine, getCmakeDirectory());

        if(SystemUtils.IS_OS_WINDOWS){
            File debugDirectory = new File(getBinDirectory(), "Debug");
            File releaseDirectory = new File(getBinDirectory(), "Release");

            if(FileUtils.directoryContains(getBinDirectory(), debugDirectory)){
                FileUtils.copyDirectory(debugDirectory, getBinDirectory());
                FileUtils.deleteDirectory(debugDirectory);
            }
            if(FileUtils.directoryContains(getBinDirectory(), releaseDirectory)){
                FileUtils.copyDirectory(releaseDirectory, getBinDirectory());
                FileUtils.deleteDirectory(releaseDirectory);
            }
        }
    }
}
