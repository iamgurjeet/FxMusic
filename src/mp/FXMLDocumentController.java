/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXColorPicker;
//import com.jfoenix.controls.JFXSlider;
import java.awt.HeadlessException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Gurjeet
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private String filepath;
    @FXML
    private Label label;
    @FXML
    private MediaView mv;
    @FXML
    private Slider slider;
    @FXML
    private Slider vslider;
    @FXML
    private TextField naam;
    @FXML
    private Button pause;
    //private JFXButton but;
    //private JFXColorPicker cp;
    private  Media media;
    private MediaPlayer mp;
    private Label start_tym,end_tym;
    private String name;
    private ArrayList<File> list=new ArrayList<>();
  //  private JFXSlider slider;
    private FileChooser fc;
    @FXML
    public void player()
    {
         JOptionPane.showMessageDialog(null, "player");
         media =new Media(filepath);
         mp=new MediaPlayer(media);
         mv.setMediaPlayer(mp);
         //JOptionPane.showMessageDialog(null, "mv---->");
         /////////////////////////////////////////////////////
//         DoubleProperty mvw= mv.fitWidthProperty();
//         DoubleProperty mvh=mv.fitHeightProperty();
//         //JOptionPane.showMessageDialog(null, "mv---->"+mv);
//         mvw.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
//         mvh.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
           ///////////////////////////////////////////////
//         mp.setOnReady(new Runnable() {
//
//             @Override
//             public void run() {
//               slider.setMin(0.0);
//               slider.setValue(0.0);
             //  slider.getMax(mp.getTotalDuration().toSeconds());
               
//             }
//         });
         vslider.setValue(mp.getVolume()*100);
         vslider.valueProperty().addListener((Observable observable) -> {
             mp.setVolume(vslider.getValue()/100);
         });
         
         move();
//            mv.setPreserveRatio(true);
//            JOptionPane.showMessageDialog(null, "mv---->"+mv+"mp---->"+mp);
//            Duration start= mp.getStartTime();
//            start_tym.setText(String.valueOf(start));
//            System.out.println(""+start);


          
    }
    private FXMLDocumentController()
    {
         System.out.println("------------------------------");
         if(!list.isEmpty())
            for(int i=0;i<list.size();i++)
            {
                System.out.println("===========>"+list.get(i));
            }
         else
         {
             System.out.println("no file ");
         }
        
    }
    public void handleButtonAction() {
        try
        {
            fc=new FileChooser();
            FileChooser.ExtensionFilter fillter =new FileChooser.ExtensionFilter("selct song", "*.mp3","*.mp4");
            fc.getExtensionFilters().add(fillter);
            File f=fc.showOpenDialog(null);
            name=f.getName();
            list.add(f);
            JOptionPane.showMessageDialog(null,"--6---->"+f.toURI());
            filepath=String.valueOf(f.toURI()); 
        }
        catch(HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,""+e);
   
        }
    }
    public void ply()
    {
         if(filepath != null)
        {  if(mp==null)
            {
            player();
            mp.play();
            naam.setText(name);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"music playing");
                mp.stop();
                player();
                mp.play();
                naam.setText(name);
            }
        }
        else{
             
            JOptionPane.showMessageDialog(null, "no file");
        }
    
    }
   public void pose()
   {
        switch (String.valueOf(mp.getStatus())) {
            case "PLAYING":
                System.out.println(mp.getStatus());
                mp.pause();
                System.out.println(mp.getStatus());
                break;
            case "PAUSED":
                System.out.println( mp.getStatus());
                Duration d= mp.getCurrentTime();
                mp.setStartTime(d);
                mp.play();
                System.out.println( mp.getStatus());
                break;
        }
   }
   public void stp()
   {
       if(String.valueOf(mp.getStatus()).equals("PLAYING"))
       {
           System.out.println( mp.getStatus());
           mp.stop();
           System.out.println( mp.getStatus());
          // filepath="";
       }
   }
   float i=1;
   public void fast()
   {
       i=(float) (i+0.15);
       JOptionPane.showMessageDialog(null, ""+i);
       mp.setRate(i);
   }
   
   public void slow()
   {
       i=(float) (i-0.15);
        JOptionPane.showMessageDialog(null, "s  "+i);
       mp.setRate(i);
   }
     int track=-1;
    public void nxt()
    {
        if(Folder.LIST.size()>track++ && track>-1)
        {
            JOptionPane.showMessageDialog(null,Folder.LIST.get(track).toString());
            File f=Folder.LIST.get(track);
            name=f.getName();
            list.add(f);
            filepath = f.toURI().toString();
           
        }
        else
        {
            JOptionPane.showMessageDialog(null,"NO Track");
        }
    }
    public void prev()
    {
        
    }
    public void openFolder()
    { 
        
        DirectoryChooser chooser=new DirectoryChooser();
        chooser.setTitle("SELECT YOUR FOLDER");
        File sel=chooser.showDialog(null);
        JOptionPane.showMessageDialog(null,"====="+sel);
        new Folder().listFolder(sel);
       
    }
   void move()
   {
//       long durationnano=mp.get
       
       mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
           @Override
           public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
               double seconds=newValue.toSeconds();
               double total=mp.getTotalDuration().toSeconds();
               slider.setValue(seconds/total*100);
           }
       });
       slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               double total=mp.getTotalDuration().toSeconds();
           mp.seek(Duration.seconds(slider.getValue()*total/100));
           }
       });
   }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    }    
    
}
