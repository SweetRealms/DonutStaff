package net.donucraft.files;

import me.yushust.inject.Module;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileMatcher {

    private final Map<String, FileCreator> files = new HashMap<>();

    public FileMatcher bind(String name, FileCreator file) {
        files.put(name, file);

        return this;
    }

    public Optional<FileCreator> get(String name) {
        return Optional.ofNullable(files.get(name));
    }

    public Module build() {
        return binder -> files.forEach((name, file) -> binder.bind(FileCreator.class).named(name).toInstance(file));
    }


}
