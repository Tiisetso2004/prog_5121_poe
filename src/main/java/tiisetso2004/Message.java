package tiisetso2004;

public class Message {

    public Message() {
    }

    public static String GenerateMessageID(int length) {
        String charset = "4682519730"; //define characterset at random.
        StringBuilder sb = new StringBuilder(length); //use string builder for a mutable string object.

        for (int i = 0; i < length; i++) {
            int index = (int) (charset.length() * Math.random()); //Math.Random returns 0.0 --> 1.0 cast it to int to get a valid whole number index.    
            sb.append(charset.charAt(index)); //add a randomly selected character to the string builder object.
        }
        //return it as string matching method return type.
        return sb.toString();
    } 

    //checks if the ID exceeds the 10 character limit.
    public boolean checkMessageID(String ID) {
        if(Validator.nullCheck(ID) || ID.length() > 10) {
            System.err.println("Ivalid ID: The ID is empty or exceeds 10 character limit");
            return false;
        } else {
            System.out.println("ID sucessfully validated");
            return true;
        }
    }

    //reuse static helper defined in Validator class for cellphone number validation.
    public String checkRecepientCell(String cellphoneNumber) {
        if(!Validator.checkCellphoneNumber(cellphoneNumber)) {
            System.err.println(MessageLog.getCellphoneErrorMessage());
            return MessageLog.getCellphoneErrorMessage();
        } else {
            System.out.println(MessageLog.getCellphoneErrorMessage());
            return cellphoneNumber;
        }
    }
    //TODO: use substrings and string builder to append message hash
    public boolean createMessageHash() {
        return true;
    }

    //----------UI----------//
    //send, disregard or store message
    public void SendMessage() {
    } 

    //prints all the messages from the start of the program
    public String printMessages() {
        return "";
    }

    public String returnTotalMessages() {
        return "";
    }

    public void StoreMessage() {
        
    }
}