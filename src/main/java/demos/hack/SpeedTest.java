package demos.hack;

import demos.service.CryptoService;

public class SpeedTest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CryptoService cryptoService = new CryptoService();
        String passwordhash = cryptoService.hashPassword("my password");
        int guess = 0;
        while (System.currentTimeMillis() - startTime < 1000 * 60) {
            String guessString = getGuess(++guess);
            if (passwordhash.equals(cryptoService.hashPassword(guessString))) {
                System.out.printf("Got it in %d seconds.%n", (System.currentTimeMillis() - startTime) / 1000l);
                System.exit(0);
            }
        }
        System.out.printf("Made %s guesses in %d seconds.%n", guess, ((System.currentTimeMillis() - startTime) / 1000l));
    }

    private static String getGuess(final int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i< (index % 26); i++) {
            builder.append(new Integer(i).toString());
        }
        return builder.toString();
    }
}
