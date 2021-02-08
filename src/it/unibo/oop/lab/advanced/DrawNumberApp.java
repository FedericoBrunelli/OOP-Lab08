package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    //private static final int MIN = 0;
    //private static final int MAX = 100;
    //private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * 
     */
    public DrawNumberApp(final String fileConfig, final DrawNumberView... views) {
        //this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        //this.view = new DrawNumberViewImpl();
        //this.view.setObserver(this);
        //this.view.start();
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        
        final Configuration.Builder confBuilder = new Configuration.Builder();
        try(var contenuti = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileConfig)))){
            for (var line = contenuti.readLine(); line != null; line = contenuti.readLine()) {
                final String[] listLine = line.split(":");
                if (listLine.length == 2) {
                    final int valore = Integer.parseInt(listLine[1].trim());
                    if (listLine[0].contains("max")) {
                        confBuilder.setMax(valore);
                    } else if (listLine[0].contains("min")) {
                        confBuilder.setMin(valore);
                    } else if (listLine[0].contains("attempts")) {
                        confBuilder.setTentativi(valore);
                    }
                } else {
                    for (final DrawNumberView view: views) {
                        view.displayError("erroe");
                    }
                }
            }
        }catch (IOException | NumberFormatException e) {
            for (final DrawNumberView view: views) {
                view.displayError(e.getMessage());
            }
        }
        
        
        final Configuration config = confBuilder.build();
        if (config.isValid()) {
            this.model = new DrawNumberImpl(config);
        }else {
            for (final DrawNumberView view: views) {
            view.displayError("Inconsistent configuration: "
                    + "min: " + config.getMin() + ", "
                    + "max: " + config.getMax() + ", "
                    + "attempts: " + config.getTentativi() + ". Using defaults instead.");
            }
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view: views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) throws FileNotFoundException{
        new DrawNumberApp("config.yml", new DrawNumberViewImpl(), new DrawNumberViewImpl(), new PrintStreamView(System.out), new PrintStreamView("output.log"));
    }

}
