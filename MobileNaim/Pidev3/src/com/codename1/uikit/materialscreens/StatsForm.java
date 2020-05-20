


/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PieChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.ratingactivite;
import com.mycompany.myapp.services.RatingService;
import java.util.ArrayList;

/**
 *
 * @author Shai Almog
 */
public class StatsForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee,0xFF0080,0x000040};
    private static final String[] LABELS = {"Anglais", "Math", "Coloriage","Sport","Dessin"};
    User u;
       String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    public StatsForm(Resources res,User u) {
       
        
        super(new BorderLayout());
        this.u=u;
        url=url+u.getImage();
        ratingactivite a=new ratingactivite();
        int Anglais=0;
         int Math=0; 
         int Sport=0;
          int Coloriage=0;
          int Dessin=0;
          ArrayList<ratingactivite> array=new ArrayList<>();
          array=RatingService.getInstance().getAllTasks();
        for (int i=0; i<=array.size()-1; i++) 
{ 
   // System.out.println(i);
//    
  a=array.get(i);
   //   System.out.println(a);

     if (a.getIntitule().equals("Anglais"))
             Anglais++;
      if (a.getIntitule().equals("Math"))
             Math++;
       if (a.getIntitule().equals("Sport"))
             Sport++;
        if (a.getIntitule().equals("Coloriage"))
             Coloriage++;
      if (a.getIntitule().equals("Dessin"))
             Dessin++;
     
}
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("gradient-overlay.png");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Staistiques ", "WelcomeBlue"),
                                new Label("des activités", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        XYMultipleSeriesDataset multi = new XYMultipleSeriesDataset();

       XYSeries seriesXY = new XYSeries("AAA", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(100,100);
        seriesXY.add(Math, Math);
        seriesXY.add(5, 5);
        seriesXY.add(6, 4);
        seriesXY.add(7, 2);
        seriesXY.add(8, 5);

        seriesXY = new XYSeries("BBB", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 7);
        seriesXY.add(4, 6);
        seriesXY.add(5, 3);
        seriesXY.add(6, 2);
        seriesXY.add(7, 1);
        seriesXY.add(8, 4);
        
         seriesXY = new XYSeries("CCC", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 7);
        seriesXY.add(2, 6);
        seriesXY.add(1, 0);
      
         
        
        
        
        
        
        
        
        
        
        

        XYMultipleSeriesRenderer renderer = createChartMultiRenderer();
        
//        CubicLineChart chart = new CubicLineChart(multi, renderer,
//                0f);
        CategorySeries C=new CategorySeries("coucou");
       
        C.add("Math",Math);
        C.add("Anglais",Anglais);
        C.add("Coloriage",Coloriage);
       C.add("Sport",Sport);
       C.add("Dessin",Dessin);
     
        PieChart chart=new PieChart(C, renderer);
   
        Container enclosure = BorderLayout.center(new ChartComponent(chart)).
                add(BorderLayout.NORTH, FlowLayout.encloseCenter(
                        new Label(LABELS[0], colorCircle(COLORS[0])),
                        new Label(LABELS[1], colorCircle(COLORS[1])),
                        new Label(LABELS[2], colorCircle(COLORS[2])),
                        new Label(LABELS[3], colorCircle(COLORS[3])),
                        new Label(LABELS[4], colorCircle(COLORS[4]))

                ));
        
        add(BorderLayout.CENTER, 
                enclosure);
                setupSideMenu(res,this.u);

//         setupSideMenu(res,u);
    }

    private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
   
        return i;
    }
    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res,u).show();
    }

    private XYMultipleSeriesRenderer createChartMultiRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        for(int color : COLORS) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            r.setFillPoints(false);
            XYSeriesRenderer.FillOutsideLine outline = new XYSeriesRenderer.FillOutsideLine(XYSeriesRenderer.FillOutsideLine.Type.BELOW);
            outline.setColor(color);
            r.addFillOutsideLine(outline);
            r.setLineWidth(5);
        }
        renderer.setPointSize(5f);
        renderer.setLabelsColor(0);
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setAxesColor(COLORS[0]);

        renderer.setXTitle("");
        renderer.setYTitle("");
        renderer.setAxesColor(0xcccccc);
        renderer.setLabelsColor(0);
        renderer.setXLabels(5);
        renderer.setYLabels(5);
        renderer.setShowGrid(true);
        
        renderer.setMargins(new int[] {0, 0, 0, 0});
        renderer.setMarginsColor(0xffffff);

        renderer.setShowLegend(false);
        
        renderer.setXAxisMin(5);
        renderer.setXAxisMax(8);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10);
        return renderer;
    }
















}
