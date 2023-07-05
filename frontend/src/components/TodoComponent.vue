<template>
  <div class="todo">
     <input type="checkbox" v-model="estTermine" @change="changeTodoState"/>


      <ul class="text-date-todo">
          <li class="test-todo">{{ description }}</li>
          <li class="date-todo">{{ formateDate }}</li>
      </ul>
      <div class="edit-delete-todo">
        <button class="update-todo-button" @click="changeDisplayState"><i class="far fa-edit"></i></button>
        <button class="delete-todo-button" @click="deleteTodo"><i class="far fa-trash-alt"></i></button>
      </div>
      <ModifierTodo 
        class="update-todo" 
        :class="{'display-flex': displayFlex}"
        :nom="this.nom"
        :description="this.description"
        :dateFin="this.dateFin"
        :liste_id="this.liste_id"
        :listes="this.listes"
        @update-todo="emitUpdate"
        @cancel-modif="changeDisplayState"
       ></ModifierTodo>
  </div>
</template>

<script>
import ModifierTodo from './ModifierTodo.vue';

export default {
    name: 'TodoComponent',
    components: {
        ModifierTodo
    },
    data() {
        return {
            estTermine: this.termine,
            displayFlex: false
        }
    },
    props: {
        id: {
            type: Number,
            required: true
        },
        nom: {
            type: String,
            required: true
        },
        description: {
            type:String,
            required: true
        },
        dateFin: {
            type: String
        },
        termine: {
            type: Boolean
        },
        liste_id: {
            type: Number
        },
        listes: {
            type: Array,
            required: true
        }
    },
    computed: {
        formateDate() {
            console.log(this.dateFin)
            if(this.dateFin !== '') {
                let date = new Date(this.dateFin);
                return `${date.getDate() < 10 ? '0'+date.getDate() : date.getDate() }/${(date.getMonth() + 1) < 10 ? '0'+(date.getMonth() + 1) : date.getMonth() + 1 }/${date.getFullYear()}`
            } else {
                return '';
            }
        }
    },
    methods: {
       changeTodoState() {
            console.log("estatus")
            this.$emit('change-todo-state', {
               termine: this.estTermine
            });
        },
        deleteTodo() {
            this.$emit('delete-todo', {});
        },
        changeDisplayState() {
            console.log("valentin")
            this.displayFlex = !this.displayFlex
        },
        emitUpdate(payload) {
            this.$emit('change-todo-state', payload);
            this.changeDisplayState();
        },

    }

    
}
</script>

<style>
.todos{
    display: flex;
    align-self: center;
    flex-direction: column;
    width: 80%;
}
.todo {
    display: flex; 
    align-items: center;
    border-bottom: 1px solid grey;
    height: 50px;
}
.todo input {
    display: flex;
    align-self: center;
    margin: 10px;
    height: 15px;
    width: 200px;
    color: white;
    background-color: grey;
}
.todo li {
    list-style: none;
}
.edit-delete-todo {
    display: flex;
    justify-content: center;
    align-self: center;
}
.todo button {
    border: none;
    height: 30px;
    align-self: center;
}
.text-date-todo {
    flex-grow: 2;
}


.update-todo {
  display: none;
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background-color: rgba(10,10,10,0.7);
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.display-flex {
  display: flex;
}

.update-todo-button, .delete-todo-button {
    color: grey;
    background-color: azure;
    margin-right: 20px;
}

@media screen and (min-width: 400px) and (max-width: 992px)
{
    .todo{
        display: flex;
        justify-content: flex-start;
        
    }
    .update-todo{
        height: 50%;
    }
}
</style>