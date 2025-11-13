@Nested
@DisplayName("String Compression Tests")
class StringCompressionTests {

    @Test
    @DisplayName("should compress repeated characters")
    void shouldCompressRepeatedCharacters() {
        String result = processor.compress("aaabbccccd");
        assertEquals("a3b2c4d1", result);
    }
}
