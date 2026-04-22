package tiisetso2004;

public class Message {

    public Message() {
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
            return MessageLog.getCellphoneErrorMessage();
        } else {
            System.err.println(MessageLog.getCellphoneErrorMessage());
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