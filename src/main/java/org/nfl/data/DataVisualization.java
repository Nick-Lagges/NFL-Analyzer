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

    private String PLAYER_1;
    private String PLAYER_2;
    private boolean COMPARE = false;

    private String NAME;
    private Integer YEAR;
    private String STAT;
    private double LINE = 0.0;
    private int GAME_COUNT;

    public DataVisualization( String player1, String player2, Integer year, String stat, int gameCount) throws IOException {
        super("Comparison");
        PLAYER_1 = player1;
        PLAYER_2 = player2;
        COMPARE = true;
        YEAR = year;
        STAT = stat;
        GAME_COUNT = gameCount;
        JFreeChart barChart = ChartFactory.createBarChart(
                YEAR.toString(),
                "game number",
                STAT,
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    public DataVisualization( String playerName, Integer year, String stat, double line, int gameCount) throws IOException {
        super(playerName);
        NAME = playerName;
        YEAR = year;
        STAT = stat;
        LINE = line;
        GAME_COUNT = gameCount;
        JFreeChart barChart = ChartFactory.createBarChart(
                YEAR.toString(),
                "game number",
                STAT,
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private CategoryDataset createDataset( ) throws IOException {
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        if ( COMPARE ) {
            PlayerSearch player1 = new PlayerSearch();
            player1.init(PLAYER_1, YEAR);
            ArrayList<Integer> yardsA = player1.getStat(STAT, GAME_COUNT);
            Integer gameA = 1;
            for ( Integer yd : yardsA) {
                dataset.addValue(yd, PLAYER_1, gameA);
                gameA++;
            }
            PlayerSearch player2 = new PlayerSearch();
            player2.init(PLAYER_2, YEAR);
            ArrayList<Integer> yardsB = player2.getStat(STAT, GAME_COUNT);
            Integer gameB = 1;
            for ( Integer yd : yardsB) {
                dataset.addValue(yd, PLAYER_2, gameB);
                gameB++;
            }
        }
        else {
            PlayerSearch newPLayer = new PlayerSearch();
            newPLayer.init(NAME, YEAR);
            ArrayList<Integer> yards = newPLayer.getStat(STAT, GAME_COUNT);
            Integer game = 1;
            for ( Integer yd : yards ) {
                dataset.addValue(yd, NAME, game);
                game++;
            }
            if ( LINE > 0 ) {
                Integer gameNum = 1;
                for ( Integer yd : yards ) {
                    dataset.addValue(LINE, "Line", gameNum);
                    gameNum++;
                }
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
