package de.silveryard.basesystem.gradleplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * Created by silveryard on 08.05.17.
 */
public class BaseSystemPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        Task runCmakeTask = project.getTasks().create("runCmake", RunCmakeTask.class);
        Task createApfTask = project.getTasks().create("createAPF", CreateAPFTask.class);
    }
}
