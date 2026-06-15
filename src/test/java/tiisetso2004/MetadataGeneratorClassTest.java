package tiisetso2004;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Messaging Metadata test")
public class MetadataGeneratorClassTest {

    //test metadata generators
    @Test
    @DisplayName("MetadataGenerator ID Generator Test")
    void testMetadataGeneratorIDGenerator() {
        final int SAMPLE_SIZE = 100;
        final int ID_LENGTH = 10;
        String[] generatedIds = new String[SAMPLE_SIZE];

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            //generate 100 new IDs in an array
            generatedIds[i] = MetadataGenerator.GenerateMessageID(ID_LENGTH);

            //assert every input is not null
            assertNotNull(generatedIds[i]);

            //assert if all meet length
            assertEquals(ID_LENGTH, generatedIds[i].length());
        } 
    }

    @Test
    @DisplayName("Hash Generator Test")
    void testMetadataGeneratorHashGenerator() {
        String messageID = "1234567890", message = "Hello world!";
        int messageNum = 45;
        assertEquals("12:45:Helloworld!", MetadataGenerator.createMessageHash(messageID,messageNum,message));
    }
}