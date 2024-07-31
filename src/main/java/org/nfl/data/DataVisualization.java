package org.nfl.data;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.util.ArrayList;

public class DataVisualization extends ApplicationFrame {

    private String NAME;
    private Player PLAYER;
    private final Integer YEAR;
    private final String STAT;
    private boolean LINE;

    public DataVisualization( Player player, String stat, boolean showLine, String type) throws IOException, InterruptedException {
        super(player.NAME);
        PLAYER = player;
        YEAR = PLAYER.YEAR;
        STAT = stat;
        LINE = showLine;
        JFreeChart barChart;
        if ( type.equals("Bar") ) {
            barChart = ChartFactory.createBarChart(
                    player.NAME,
                    "Week",
                    STAT,
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }
        else {
            barChart = ChartFactory.createLineChart(
                    player.NAME,
                    "Week",
                    STAT,
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }
        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private CategoryDataset createDataset( ) throws IOException, InterruptedException {
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );
        for ( PlayerGame game : PLAYER.getGAME_LOG().GAME_LOG ){
            if ( STAT.equals("targets") ) {
                dataset.addValue(game.getTAR(), PLAYER.NAME, game.getWEEK());
            }
            else if ( STAT.equals("receptions") ) {
                dataset.addValue(game.getREC(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getRecLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("receiving yards") ) {
                dataset.addValue(game.getREC_YD(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getRecYdLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("receiving touchdowns") ) {
                dataset.addValue(game.getREC_TD(), PLAYER.NAME, game.getWEEK());
            }
            else if ( STAT.equals("rushing attempts") ) {
                dataset.addValue(game.getRUSH_ATT(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getRushAttLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("rushing yards") ) {
                dataset.addValue(game.getRUSH_YD(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getRushYdLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("rushing touchdowns") ) {
                dataset.addValue(game.getRUSH_TD(), PLAYER.NAME, game.getWEEK());
            }
            else if ( STAT.equals("passing attempts") ) {
                dataset.addValue(game.getPASS_ATT(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getPassAttLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("passing completions") ) {
                dataset.addValue(game.getPASS_COMP(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getPassCompLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("passing yards") ) {
                dataset.addValue(game.getPASS_YD(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getPassYdLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("passing touchdowns") ) {
                dataset.addValue(game.getPASS_TD(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getPassTDLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("passing interceptions") ) {
                dataset.addValue(game.getPASS_INT(), PLAYER.NAME, game.getWEEK());
                if ( LINE ) dataset.addValue(game.getPassIntLine(), "Line", game.getWEEK());
            }
            else if ( STAT.equals("offensive snaps") ) {
                dataset.addValue(game.getOFF_SNAP(), PLAYER.NAME, game.getWEEK());
            }
            else if ( STAT.equals("week") ) {
                dataset.addValue(game.getWEEK(), PLAYER.NAME, game.getWEEK());
            }
        }
        return dataset;
    }

    public void start() {
        this.pack( );
        RefineryUtilities.centerFrameOnScreen( this );
        this.setVisible( true );
    }

}
