<template>
    <div class="form-title-add">
        <h2>Ajouter une liste todo</h2>
        <form class="add-list-form">
            <input type="text" v-model="newListe.nom" placeholder="Nom de la liste" />
            <div class="buttons">   
                <button class="cancel-button" @click.prevent="cancelCreation">Annuler</button>
                <button class="add-button" :disabled="!formValid" @click.prevent="addNewListe">Ajouter</button>
            </div>
        </form>
    </div>
</template>

<script>

export default {
    name: 'AjouterListe',
    data(){
        return {
            newListe: {
                nom: ''
            }
        }
    },
    computed: {
        formValid() {
            if(this.newListe.nom !== '') {
                return true;
            } else {
                return false;
            }
        }
    },
    methods: {
        addNewListe() {
            this.$emit('add-new-list', {
                ...this.newListe
            })
            
        },
        cancelCreation() {
            this.$emit('cancel-list-creation');
        }
        
        
    }

}
</script>

<style>

.form-title-add{
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-self: flex-start;
    border-radius: 20px;
}
.add-list-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 20px;
    border-radius: 20px;
}

.add-list-form input, textarea, select {
    border-radius: 5px;
    padding: 10px;
    border: 1px solid grey; 
}

.form-title-add h2 {
    color: grey;
    font-size: medium;
}

.form-title-add .buttons{
    align-self: flex-start;
}

.add-button, .cancel-button {
    border-radius: 8px;
}

.add-button {
    background-color: blueviolet;
    border: 1px solid blueviolet;
}

.cancel-button {
    background-color: grey;
    border: 1px solid grey;
    margin-right: 10px;
}
</style>