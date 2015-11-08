package io.anthonysantos;

import static spark.Spark.get;

public class BlogService {
    public static void main(String[] args) {
        get("/posts", (req, res) -> "Hello Sparkingly World!");
    }
}
