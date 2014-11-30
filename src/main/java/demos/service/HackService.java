package demos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HackService {

    private final Map<String, String> passwordMapping;
    private final CryptoService cryptoService;

    @Autowired
    public HackService(final CryptoService cryptoService) {
        this.cryptoService = cryptoService;
        passwordMapping = loadDictionary();
    }

    private Map<String, String> loadDictionary() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/pwd.txt")))) {
            return in.lines().collect(Collectors.toMap(
                    this::md5Hash,
                    pwd -> pwd
            ));
        } catch (final IOException e) {
            throw new RuntimeException("Failed to initialize.", e);
        }
    }

    private String md5Hash(final String hash) {
        return cryptoService.hashPassword(hash);
    }

    public HackResult reverseHash(final String hash) {
        final String pwd = passwordMapping.get(hash);
        return new HackResult(hash, pwd);
    }

    public static class HackResult {

        private final String hash;
        private final Optional<String> pwd;

        public HackResult(final String hash, final String pwd) {

            this.hash = hash;
            this.pwd = Optional.ofNullable(pwd);
        }

        public String getMessage() {
           return String.format("The result for %s is %s", hash, pwd.orElseGet(() -> "not found"));
        }

        public boolean found() {
            return pwd.isPresent();
        }
    }
}
