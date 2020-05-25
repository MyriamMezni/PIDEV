/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CommandeStat;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceCommandeStat;
import java.util.ArrayList;
import javafx.scene.control.ToolBar;





/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Statistique sur les menus commandés";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The budget per project for this year (pie chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      ArrayList<CommandeStat> commandes=ServiceCommandeStat.getInstance().getStat();
      int valueSizeArray=commandes.size();
    double[] values = new double[valueSizeArray];
    ArrayList<Integer> colorsArray=new ArrayList<>();
    colorsArray.add(ColorUtil.BLACK);
    colorsArray.add(ColorUtil.BLUE);
    colorsArray.add(ColorUtil.GRAY);
    colorsArray.add(ColorUtil.LTGRAY);
    colorsArray.add(ColorUtil.MAGENTA);
    int[] colors = new int[valueSizeArray];
    for(int i=0;i<valueSizeArray;i++)
    {
        colors[i]=colorsArray.get(i);
        values[i]=commandes.get(i).getNbr();
    }
    
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);

   
    final CategorySeries seriesSet = buildCategoryDataset("Stat sur es commandes", values,commandes);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    Form f=wrap("Stat sur les menus", comp);
      Button b=new Button("Retour");
      b.addActionListener((evt) -> {
         
      });
    return f;
    
  }
  
  public Form execute(Resources res,User u) {
      ArrayList<CommandeStat> commandes=ServiceCommandeStat.getInstance().getStat();
      int valueSizeArray=commandes.size();
    double[] values = new double[valueSizeArray];
    ArrayList<Integer> colorsArray=new ArrayList<>();
    colorsArray.add(ColorUtil.BLACK);
    colorsArray.add(ColorUtil.BLUE);
    colorsArray.add(ColorUtil.GRAY);
    colorsArray.add(ColorUtil.LTGRAY);
    colorsArray.add(ColorUtil.MAGENTA);
    int[] colors = new int[valueSizeArray];
    for(int i=0;i<valueSizeArray;i++)
    {
        colors[i]=colorsArray.get(i);
        values[i]=commandes.get(i).getNbr();
    }
    
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);

   
    final CategorySeries seriesSet = buildCategoryDataset("Stat sur es commandes", values,commandes);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    Form f=wrap("Stat sur les menus", comp);
      Button b=new Button("Retour");
      b.addActionListener((evt) -> {
          new AllMenusForm(res,u).show();
      });
      Toolbar tb=f.getToolbar();
      tb.addCommandToLeftSideMenu("Retour", null, (evt) -> {
          new AllMenusForm(res,u).show();
      });
      //f.add(b);
    return f;
    
  }

}
