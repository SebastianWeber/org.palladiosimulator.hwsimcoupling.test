package org.palladiosimulator.hwsimcoupling.util.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.hwsimcoupling.util.impl.FileManager;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.CommandHandlerMock;

public class FileManagerImplTest {

    @BeforeEach
    void clearFiles() {
        TestHelper.setFileContent("", "copy");
        TestHelper.setFileContent("", "simulation");
        TestHelper.setFileContent("", "extraction");
    }

    @Test
    void testAbsolutePaths() {
        Map<String, Serializable> parameterMap = new HashMap<String, Serializable>();
        parameterMap.put("test1", "absolute:nonexistent1");
        parameterMap.put("test2", "absolute:nonexistent2");
        parameterMap.put("test3", "absolute:nonexistent3");
        Map<String, Serializable> result = FileManager.copyFiles(parameterMap, new CommandHandlerMock());
        assertEquals("nonexistent1", result.get("test1"));
        assertEquals("nonexistent2", result.get("test2"));
        assertEquals("nonexistent3", result.get("test3"));
    }

    @Test
    void testLocalPaths() {
        try {
            IProject project = ResourcesPlugin.getWorkspace()
                .getRoot()
                .getProject("test_project");
            project.create(null);
            project.open(null);
            IFile file = project.getFile("test_file");
            file.create(new ByteArrayInputStream("test".getBytes()), false, null);
            Map<String, Serializable> parameterMap = new HashMap<String, Serializable>();
            parameterMap.put("test", "local:test_project/test_file");
            Map<String, Serializable> result = FileManager.copyFiles(parameterMap, new CommandHandlerMock());
            assertEquals(file.getLocationURI()
                .toString()
                .replace("file:/", ""), result.get("test"));
        } catch (CoreException e) {
            assertTrue(false, "Local path resolving not working: " + e.getMessage()
                    + ". If you don't run the test as plugin test \"IllegalStateExceptionis: Workspace is closed\" is expected.");
        }
    }

}
