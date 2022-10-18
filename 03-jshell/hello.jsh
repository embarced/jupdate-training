"!dlroW olleH"

String flip(String s) {
    StringBuilder flippedString = new StringBuilder();
    for (int i = (s.length() - 1); i >= 0; i--) {
        flippedString.append(s.charAt(i));
    }
    return flippedString.toString();
}

String hello = flip($1)

System.out.println(hello)
