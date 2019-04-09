/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author gurjeet
 */
public class Folder {
   static ArrayList<File> LIST;
     private JFileChooser fc;
    void listFolder(File dir)
    {
         
        File[] subDirs= dir.listFiles((File pathname) -> pathname.isDirectory());
         JOptionPane.showMessageDialog(null,"====="+dir);
        System.out.println("Directory of ===>"+dir.getAbsolutePath());
        listFile(dir);
         JOptionPane.showMessageDialog(null,"=====");
        for(File folder: subDirs)
        {
            listFolder(folder);
        }
    }
    public  void listFile(File dir)
    {
        File[] files=dir.listFiles();
         LIST=new ArrayList<>();
        for(File file:files)
        {
             //JOptionPane.showMessageDialog(null,"=====");
            //System.out.println("File-->"+file.getName());
             //JOptionPane.showMessageDialog(null,"--->"+file);
           LIST.add(file);
            
        }
    }
//    public static void main(String[] args) {
//        
//        new Folder().listFolder(new File("D:\\photoshop"));
//       
//       
//    }
    
}
