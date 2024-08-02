package org.nfl.data;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;

public class ModelTesting extends ApplicationFrame {
    private String[] playerList = { "DJ Moore", "AJ Brown", "Keenan Allen", "Brandon Aiyuk", "Tyreek Hill", "CeeDee Lamb", "Michael Pittman", "Stefon Diggs", "Ja'Marr Chase", "Davante Adams", "Adam Thielen", "Garrett Wilson", "Chris Olave", "Chris Godwin", "Mike Evans", "DeVonta Smith", "Nico Collins", "Amari Cooper", "Jaylen Waddle", "Justin Jefferson" };
    private TeamDefense team = null;
    private DefenseStats defenseStats = new DefenseStats(2022);

    public ModelTesting() throws IOException, InterruptedException {
        super("testing");

        JFreeChart barChart;
        barChart = ChartFactory.createScatterPlot(
                "Incorrect Predictions",
                "Model Prediction",
                "Line",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
    XYDataset createDataset() throws IOException, InterruptedException {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries correct = new XYSeries("correct");
        XYSeries incorrect = new XYSeries("incorrect");

        correct = modelTester(correct, true);
        incorrect = modelTester(incorrect, false);

        dataset.addSeries(incorrect);
        dataset.addSeries(correct);

        return dataset;
    }

    public XYSeries windVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(game.getWindSpeed(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries feelsLikeVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(game.getFeelsLike(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries recPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getReceptionsPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries tarPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getTargetsPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries pointsPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getPointsPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries playsPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getPlaysPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries expectedPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getExpectedPointsPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries passTdPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getPassAttPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries yardsPGVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getYardsPG(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries yardsPPVsRec(XYSeries indep) throws IOException, InterruptedException {
        Player player = null;
        for ( String name : playerList ) {
            player = new Player(name, 2023);
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                for (TeamDefense teamDefense : defenseStats.getNFL_DEFENSES() ) {
                    if (teamDefense.getTeamName().toLowerCase().contains(Utils.ABBR_TO_TEAM.get(game.getOPPONENT())))
                        team = teamDefense;
                }
                if ((game.getRecLine() != 0) && game.getREC() != -1) {
                    indep.add(team.getYardsPerPlay(), game.getREC());
                }
            }

        }
        return indep;
    }

    public XYSeries modelTester(XYSeries indep, boolean correctness) throws IOException, InterruptedException {
        Player player = null;
        ModelA modelA = new ModelA(2022);
        double line;
        double pred;
        int act;
        boolean c;
        int correct = 0;
        int gameNum = 1;
        int realOvers = 0;
        int realUnders = 0;
        int correctOvers = 0;
        int correctUnders = 0;
        for (String name : playerList) {
            player = new Player(name, 2023);
            PlayerSeasonStats stats = player.getSEASON_STATS(2022);
            double avgRec = (double) stats.getReceptions() / stats.getGames();
            for (PlayerGame game : player.getGAME_LOG().GAME_LOG) {
                line = game.getRecLine();
                if (line != 0.0) {
                    pred = modelA.performModel("receptions", game, avgRec, line);
                    act = game.getREC();
                    c = (((pred > line) && (act > line)) || ((pred < line) && (act < line)));
                    double conf = pred-line;
                    double diff = pred-act;
                    if (c){
                        correct++;
                        if ( pred < line ) correctUnders++;
                        else  correctOvers++;
                        if (correctness) indep.add(game.getRecLine(), game.getREC());
                    }
                    else {
                        if (!correctness) indep.add(game.getRecLine(), game.getREC());
                    }
                    if ( act > line ) realOvers++;
                    else realUnders++;
                    gameNum++;
                }
            }
        }
        return indep;
    }

    public void start() {
        this.pack( );
        RefineryUtilities.centerFrameOnScreen( this );
        this.setVisible( true );
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Utils utils = new Utils();
        ModelTesting modelTesting = new ModelTesting();
        modelTesting.start();
    }

}
