
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Conversation implements ConversationRequirements {

  // Attributes 
  int rounds;
  ArrayList<String> convo;
  

  /**
   * Constructor 
   */
  public Conversation() {
    this.rounds = -1; 
    this.convo = new ArrayList<>(); 
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    // These are the variables that I need
    Scanner input = new Scanner(System.in);
    String prompt; 
    int i;
    String ans;
    Scanner num = new Scanner(System.in);

    //How many rounds
    System.out.println("How many rounds?");
    this.rounds = num.nextInt();
    

    //Beginning
    System.out.println("Welcome! What's on your mind?");
    
    //Goes through each round and prompts user for prompt then uses respond method to generate response
    for (i = 1; i<= this.rounds; i++) {
        prompt = input.nextLine();
        ans = respond(prompt);
        System.out.println(respond(prompt));
        this.convo.add(prompt);
        this.convo.add(ans);
    } 
    //Ending
    System.out.println("Bye!");
    input.close(); 
    num.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    // Printing the part that is the same for each
    System.out.println(" ");
    System.out.println("TRANSCRIPT:");
    System.out.println("Welcome! What's on your mind?");

    // Printing the conversation from chat
    for (int i = 0; i < 2*this.rounds; i++) {
        System.out.println(this.convo.get(i));
    }
    System.out.println("Bye!");
    
    }  
  
  

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    //Create array for all words in prompt
    ArrayList<String> inputList = new ArrayList<>();
    String[] ToAdd = inputString.split(" ");
    String returnString;

    //Adding all the words to our array
    for (int i = 0; i < ToAdd.length; i++) {
      if ("I".equals(ToAdd[i])) {
        inputList.add("you");
      } else if ("you".equals(ToAdd[i])) {
        inputList.add("I");  
      } else if ("me".equals(ToAdd[i])) {
        inputList.add("you");  
      } else if ("am".equals(ToAdd[i])) {
        inputList.add("are");
      } else if ("my".equals(ToAdd[i])) {
        inputList.add("your");
      } else if ("your".equals(ToAdd[i])) {
        inputList.add("my");
      } else {
          inputList.add(ToAdd[i]);
      }
           
    }

    //Checking if the array has any of the mirror words
    if (inputList.contains("I") || inputList.contains("you") || inputList.contains("me") || inputList.contains("am") || inputList.contains("my") || inputList.contains("your")) {
      returnString = "";
      //Creating response
      for (int i = 0; i < ToAdd.length; i++) {
          returnString = returnString + inputList.get(i) + " ";
      } 
      
    } else {
        // Creating list of random responses for when there's no key words 
        String[] responses = {"Uh huh", "Awesome", "Hmmm", "Interesting"};

        // Using random to randomize which of our potential reponses it picks 
        Random random = new Random();
        int response_num = random.nextInt(responses.length);

        returnString = responses[response_num];
    }
      

    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
