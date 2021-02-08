package it.unibo.oop.lab.advanced;

public final class Configuration {
    
    private final int min;
    private final int max;
    private final int tentativi;
    
    private Configuration(final int min, final int max, final int tentativi) {
        this.min = min;
        this.max = max;
        this.tentativi = tentativi;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getTentativi() {
        return this.tentativi;
    }
    
    public boolean isValid() {
        return this.tentativi > 0 && this.min < this.max;
    }
    
    public static class Builder {
         
        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int TENTATIVI = 10;
        
        private int min = MIN;
        private int max = MAX;
        private int tentativi = TENTATIVI;
        
        private boolean disabled = false;
        
        public Builder setMin(final int min) {
            this.min = min;
            return this;
        }
        
        public Builder setMax(final int max) {
            this.max = max;
            return this;
        }
        
        public Builder setTentativi(final int tent) {
            this.tentativi = tent;
            return this;
        }
        
        public Configuration build() {
            if (disabled) {
                throw new IllegalStateException("The builder can only be used once");
            }
            this.disabled = true;
            return new Configuration(min, max, tentativi);
        }

    }
}
