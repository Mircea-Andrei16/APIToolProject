/**
 * Is a class for selecting the files that we want to extract from the disk
 * Is used with the JFileChooser
 */
package apiTestTool.construction;

import java.io.File;
import javax.swing.filechooser.FileFilter;
 
public class FileTypeFilter extends FileFilter {
    private String extension;
    private String description;
 
    public FileTypeFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }
 
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
    }
 
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
