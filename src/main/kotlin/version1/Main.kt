package version1

import kotlin.system.exitProcess

class KnockKnockPun {
    private val BARELY_THERE_PUN = "Meh. Try again."
    private val OKAY_PUN = "That's an okay attempt."
    private val GREAT_PUN = "Whooa! Pun Grandmaster here!"

    private val helpCommand = "HELP"
    private val quitCommand = "QUIT"
    private val readyCommand = "READY"

    fun introPrompt() {
        println("* Welcome to KnockKnockPuns! *\n** An interactive game that rates the puns in your knock knock joke. ** \n")
        instructions()
    }

    fun initialiseGame() {
        val startInput = readln()
        if (startInput.trim().equals(readyCommand, ignoreCase = true)) {
            punRepl()
        } else if (startInput.trim().equals(helpCommand, ignoreCase = true)) {
            help()
        } else if (startInput.trim().equals(quitCommand, ignoreCase = true)) {
            quit()
        } else {
            println("*** Wrong entry! ***")
            instructions()
            initialiseGame()
        }
    }

    private fun instructions() {
        println("---INSTRUCTIONS---")
        println("*** You can start a new round by typing: READY ***\n*** Ask for help by typing HELP or QUIT to exit the game. ***")
        println("*** Ready to begin? Enter READY at any time: ***")
    }

    private fun help() {
        println("*** READY starts a new round. ***")
        println("**** QUIT exits the game. ****")
        println("**** HELP shows the help info. ****\n")
    }

    private fun punRepl() {
        println("Enter a knock knock pun, Start by saying: knock, knock")
        val knockGreeting = readln()

        if (knockGreeting.isEmpty()) {
            println("*** Wrong entry! ***\n")
            punRepl()
        } else if (knockGreeting.equals(readyCommand, ignoreCase = true)) {
            restart()
        } else if (knockGreeting.equals(helpCommand, ignoreCase = true)) {
            help()
            punRepl()
        } else if (knockGreeting.equals(quitCommand, ignoreCase = true)) {
            quit()
        } else {

            println("${evaluatePun(knockGreeting)}\n")
            punRepl()
        }
    }

    private fun evaluatePun(knock: String): String {
        var punLevel = BARELY_THERE_PUN
        if (knock.length > 5 && knock.substring(0, 5).contains("knock", ignoreCase = true)) {
            punLevel = assessPunLevel()
        } else {
            println("\nKnock knock puns should start with knock knock.")
        }
        return punLevel
    }

    private fun assessPunLevel(): String {
        // TODO Handle the edge cases of the user interrupting with HELP or READY here.
        println("\nWho's there?")
        val name = readln()
        quitInterruption(name)

        println("\n$name who?")
        val pun = readln()
        quitInterruption(pun)

        return if (pun.contains(name, ignoreCase = true)) {
            GREAT_PUN
        } else {
            OKAY_PUN
        }
    }

    private fun restart() {
        println("Starting a new KnockKnockPun round...\n")
        punRepl()
    }

    private fun quitInterruption(input: String) {
        if (input.equals(quitCommand, ignoreCase = true)) {
            quit()
        }
    }

    private fun quit() {
        println("Bye!")
        exitProcess(1)
    }
}

fun main() {
    val knockKnock = KnockKnockPun()

    knockKnock.introPrompt()

    knockKnock.initialiseGame()
}
