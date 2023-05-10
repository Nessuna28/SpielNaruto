package Fights

import Charakters.*
import favoriteColorUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString
import teamComputer
import teamUser

fun teamFight() {}

var mainCharacterUser = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamMember1User = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember1User = ""
var teamMember2User = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember2User = ""

var mainCharacterComputer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamMember1Computer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember1Computer = ""
var teamMember2Computer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember2Computer = ""


fun sortedFunctionsForTeamPlay() {

   selectionAttackTeamUser()
    attackComputerTeam()
}

fun selectionAttackTeamUser() {

    var counter = 0

    do {
        if (mainCharacterUser.name.isNotEmpty()) {
            print("\nMöchtest du $favoriteColorUser(1) angreifen$reset oder $favoriteColorUser(2) ausweichen$reset? Gib die jeweilige Zahl ein: ")
            selectionUserInt = readln().toInt()
            if (selectionUserInt == 1) {
                mainCharacterUser.showSelection()
                counter = selectionUserInt
            } else if (selectionUserInt == 2) {
                mainCharacterUser.baumstamm("user")
                counter = selectionUserInt
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } else {
            selectionAttackUser()
        }
    } while (counter != selectionUserInt)

    mainCharacterUser.loadChakra(selectionUserString)
}

// Spieler soll sich aus dem Team einen Hauptcharakter aussuchen, der wird danach aus der Teamliste gelöscht
fun selectionMainCharacter() {

    println("\nWelcher soll dein Hauptcharakter sein?")
    print("Gib den Namen ein! : ")
    val selectionUser = readln().lowercase()
    setCharacterForTeam(selectionUser)
    teamUser.remove(mainCharacterUser)
    selectionUserString = mainCharacterUser.name
}

// diese Funktion bestimmt einen Hauptcharakter für den Computer, der wird danach aus der Teamliste gelöscht
fun selectionMainCharacterComputer() {

    mainCharacterComputer = teamComputer.random()
    teamComputer.remove(mainCharacterComputer)
}

// diese Funktion sucht für die beiden Charaktere aus dem Team zufällige Attacken aus
fun randomAttackTeamUser() {

    var attackListTeamMember1 = mutableListOf<String>()
    var attackListTeamMember2 = mutableListOf<String>()

    var counter = 0

    for (character in teamUser) {
        if (counter == 0) {
            teamMember1User = character
        } else {
            teamMember2User = character
        }
        counter = 1
    }

    if (teamMember1User is CharacterWithGenjutsu) {
        if (teamMember1User is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember1User.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.weapon)
                attackListTeamMember1.add(attack.key)

        } else {
            for (attack in teamMember1User.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.weapon)
                attackListTeamMember1.add(attack.key)
        }
    } else if (teamMember1User is CharacterWithMedicalSkills) {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)

        attackListTeamMember1.add("Heilung")

    } else if (teamMember1User is CharacterWithBijuu) {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)

    } else {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)
    }


    if (teamMember2User is CharacterWithGenjutsu) {
        if (teamMember2User is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember2User.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.weapon)
                attackListTeamMember2.add(attack.key)

        } else {
            for (attack in teamMember2User.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.weapon)
                attackListTeamMember2.add(attack.key)
        }
    } else if (teamMember2User is CharacterWithMedicalSkills) {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)

        attackListTeamMember2.add("Heilung")

    } else if (teamMember2User is CharacterWithBijuu) {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)

    } else {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)
    }

    attackTeamMember1User = attackListTeamMember1.random()
    attackTeamMember2User = attackListTeamMember2.random()

    attackTeamPrint(teamMember1User, attackTeamMember1User, teamMember2User, attackTeamMember2User)

    lostLifePointsSinglePlay(attackTeamMember1User, selectionComputer, teamMember1User, mainCharacterComputer)
    lostLifePointsSinglePlay(attackTeamMember2User, selectionComputer, teamMember2User, mainCharacterComputer)
}

fun attackComputerTeam () {

    attackComputer()
    randomAttackTeamComputer()
}
// diese Funktion sucht für die beiden Charaktere aus dem Team zufällige Attacken aus
fun randomAttackTeamComputer() {

    var attackListTeamMember1 = mutableListOf<String>()
    var attackListTeamMember2 = mutableListOf<String>()

    var counter = 0

    for (character in teamComputer) {
        if (counter == 0) {
            teamMember1Computer = character
        } else {
            teamMember2Computer = character
        }
        counter = 1
    }

    if (teamMember1Computer is CharacterWithGenjutsu) {
        if (teamMember1Computer is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember1Computer.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.weapon)
                attackListTeamMember1.add(attack.key)

        } else {
            for (attack in teamMember1Computer.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.weapon)
                attackListTeamMember1.add(attack.key)
        }
    } else if (teamMember1Computer is CharacterWithMedicalSkills) {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)

        attackListTeamMember1.add("Heilung")

    } else if (teamMember1Computer is CharacterWithBijuu) {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)

    } else {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)
    }


    if (teamMember2Computer is CharacterWithGenjutsu) {
        if (teamMember2Computer is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember2Computer.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.weapon)
                attackListTeamMember2.add(attack.key)

        } else {
            for (attack in teamMember2Computer.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.weapon)
                attackListTeamMember2.add(attack.key)
        }
    } else if (teamMember2Computer is CharacterWithMedicalSkills) {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)

        attackListTeamMember2.add("Heilung")

    } else if (teamMember2Computer is CharacterWithBijuu) {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)

    } else {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)
    }

    attackTeamMember1Computer = attackListTeamMember1.random()
    attackTeamMember2Computer = attackListTeamMember2.random()

    attackTeamPrint(teamMember1Computer, attackTeamMember1Computer, teamMember2Computer, attackTeamMember2Computer)

    lostLifePointsSinglePlay(attackTeamMember1Computer, selectionUserString, teamMember1Computer, mainCharacterUser)
    lostLifePointsSinglePlay(attackTeamMember2Computer, selectionUserString, teamMember2Computer, mainCharacterUser)
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Charakters.Character, Charakters.CharacterWithGenjutsu oder Charakters.CharacterWithMedicalSkills in der Variablen mainCharacter
fun setCharacterForTeam(string: String) {

    for (character in characterList) {
        if (character is CharacterWithMedicalSkills) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithMedicalSkills
            }
        } else if (character is CharacterWithGenjutsu) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithGenjutsu
            }
            if (character is CharacterWithGenjutsuAndSusanoo) {
                if (character.name.lowercase() == string) {
                    mainCharacterUser = character as CharacterWithGenjutsuAndSusanoo
                }
            }
        } else if (character is CharacterWithBijuu) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithBijuu
            }
        } else if (character is CharacterWithMoreStrength) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithMoreStrength
            }
        } else {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character
            }
        }
    }
}

// gibt einen Text aus, welches Teammitglied mit welcher Attacke geholfen hat
fun attackTeamPrint(member1: Character, attackMember1: String, member2: Character, attackMember2: String) {

    if (member1 == teamMember1User)
        println("${member1.name} hat dir mit $attackMember1 geholfen und \n${member2.name} hat dir mit $attackMember2 geholfen.")
    else
        println("${member1.name} und ${member2.name} haben dich mit angegriffen mit $attackMember1 und $attackMember2.")
}