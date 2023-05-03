fun fight() {
}


fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1)angreifen oder (2)ausweichen? Gib die Zahl ein: ")
        inputUser = readln()
        if (inputUser.toInt() == 1) {
            if (characterUser is CharacterWithGenjutsu) {
                println(
                    """
                        
            Mit welcher Attacke möchtest du angreifen?
            Taijutsu
            ${characterUser.ninjutsu.keys}
            Genjutsu
            ${characterUser.weapon}
           
            """.trimIndent()
                )
            } else if (characterUser is CharacterWithMedicalSkills) {
                println(
                    """
                        
            Mit welcher Attacke möchtest du angreifen?
            Taijutsu
            ${characterUser.ninjutsu.keys}
            ${characterUser.weapon}
            
            """.trimIndent()
                )
                print("Oder möchtest du dich heilen? Gib Ja oder Nein ein: ")
                inputUser = readln().lowercase()
                if (inputUser == "ja") {
                    (characterUser as CharacterWithMedicalSkills).heal()
                }
            } else {
                println(
                    """
            Mit welcher Attacke möchtest du angreifen?
            Taijutsu
            ${characterUser.ninjutsu.keys}
            ${characterUser.weapon}
            
            """.trimIndent()
                )
            }
        } else if (inputUser.toInt() == 2) {
            characterUser.baumstamm()
        }
    }
}

