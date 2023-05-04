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
            
            
           
            """.trimIndent()
                )
                print("Triff deine Auswahl per Zahl: ")
                inputUser = readln()

            } else if (characterUser is CharacterWithMedicalSkills) {
                println(
                    """
                        
            Mit welcher Attacke möchtest du angreifen?
            
            
            
            """.trimIndent()
                )
                print("Triff deine Auswahl per Zahl: ")
                inputUser = readln()
                if (inputUser.toInt() == 5) {
                    (characterUser as CharacterWithMedicalSkills).heal()
                }
            } else {
                println(
                    """
            Mit welcher Attacke möchtest du angreifen?
   
            
            
            """.trimIndent()
                )
                print("Triff deine Auswahl per Zahl: ")
                inputUser = readln()
            }
        } else if (inputUser.toInt() == 2) {
            characterUser.baumstamm()
        }
    }
}

