<template>
  <div id="app">
    <header class="app-header">
      <h1 class="app-title">Mes Todos</h1>
    </header>
    <div class="app-body">
      <section class="filter-menu">
        <div class="liste-filter">
          <select v-model="listeSelect">
            <option v-for="liste in listes" :key="liste.id" selected>{{liste.nom}}</option>
          </select>
          <button @click="() => showAddList = true">Ajouter une liste</button> 
        </div>
        <div class="finish-filter">
          <label>
            <input type="checkbox" v-model="afficherTermines" @change="!afficherTermines"/>
            ToDos terminés
          </label>
        </div>
      </section>
      <section class="todos">
        <TodoComponent v-for="(todo, index) in todosAAfficher" :key="todo.id"
          :id="todo.id"
          :nom="todo.nom"
          :description="todo.description"
          :dateFin="todo.dateFin"
          :termine="todo.termine"
          :liste_id="todo.liste_id"
          :listes="listes"
          @change-todo-state="payload => changeTodoState(payload, index)"
          @delete-todo="deleteTodo(index)">
        </TodoComponent>
      </section>
      <button class="add-todo-button" @click="changeDisplayState"><i class="fas fa-plus"></i></button>
      <section class="add-todo" :class="{'display-flex': displayFlex}">
        <AjouterTodo :listes="listes" @add-new-todo="payload => addNewTodo(payload)"></AjouterTodo>
      </section>
      <section class="add-list" v-if="showAddList">
        <AjouterListe @add-new-list="addNewListe" @cancel-list-creation="() => showAddList=false"></AjouterListe>
      </section>
      
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import TodoComponent from './components/TodoComponent.vue';
import AjouterTodo from './components/AjouterTodo.vue';
import AjouterListe from './components/AjouterListe.vue';


export default {
  name: 'app',
  components: {
    TodoComponent,
    AjouterTodo,
    AjouterListe

},
  data() {
    return {
      listes: [],
      todos: [],
      listeSelect: '',
      afficherTermines: false,
      displayFlex: false,
      showAddList: false
    }
  },
  created() {
    axios.get('http://localhost:3000/listes')
      .then(response => {
        this.listes = response.data;
        console.log(this.listes)
      })
    axios.get('http://localhost:3000/todos')
      .then(response => {
        this.todos = response.data;
      })
  },
  methods: {
    
    changeTodoState(newState, indexTodo) {
      console.log(newState, indexTodo);
      console.log('coucou');
      // const copie = Object.assign({}, this.todosAAfficher[indexTodo]);

      // @ts-ignore
      const bernard = {...this.todosAAfficher[indexTodo], ...newState};

      //copie.termine = newState.termine;
       //copie.nom = newState.nom;
       //copie.liste_id = newState.listeId;
       //copie.description = newState.description;

      console.log(bernard);
      axios.put(`http://localhost:3000/todos/${bernard.id}`, bernard)
        .then(response => {
          console.log(response.data);
          const trueIndex = this.todos.findIndex(todo => bernard.id === todo.id);
          this.todos[trueIndex] = response.data;
          this.todos = [...this.todos];
        })
    },

    async addNewListe(newListe) {
      console.log("Ajout d'une Liste");
      try {
        const rep = await axios.post('http://localhost:3000/listes', newListe);
        this.listes.push(rep.data);
      } catch(e) {
        console.error('Une erreur est survenue lors de la création du nouveau liste');
      }
    },

   addNewTodo(newTodo) {
  console.log('Ajout d\'un nouveau todo');

  const todoData = {
    nom: newTodo.nom,
    description: newTodo.description,
    dateFin: newTodo.dateFin,
    termine: false,
    liste_id: newTodo.idListe
  };

  axios.post('http://localhost:3000/todos', todoData)
    .then(response => {
      console.log('Todo ajouté:', response.data);
      this.todos.push(response.data);
      // pas besoin de recharger la page, la nouvelle tâche est déjà ajoutée dans la liste
    })
    .catch(error => {
      console.error('Une erreur est survenue lors de la création du nouveau todo');
    });
},

    changeDisplayState() {
      this.displayFlex = !this.displayFlex
    },
    deleteTodo(indexTodo) {
      const copie = Object.assign({}, this.todosAAfficher[indexTodo]);
      axios.delete(`http://localhost:3000/todos/${copie.id}`)
        .then(()=> {
          this.todosAAfficher.splice(this.todosAAfficher[indexTodo], 1);
          location.reload()
        })
    }
  },
  
  computed: {
    todosAAfficher() {
      console.log(this.todos);
      if(this.afficherTermines === false) {
        return this.todos.filter(todo => todo.termine === false)
      } else {
        return this.todos
      }
    }
  }
}
</script>

<style>
body{
  background-color: azure;
}

button {
  cursor: pointer;
}

#app {
  font-family: 'Roboto', serif;
}

.app-header {
  background-color: purple;
  height: 80px;
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
}

.app-title {
  color: white;
  margin-left: 100px;
}

.app-body {
  width: 80%;
  margin: auto;
  margin-top: 80px;
  display: flex;
  flex-direction: column;
}

.filter-menu {
  display: flex;
  justify-content: space-between;
}

.add-todo {
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

.filter-menu {
  padding: 20px;
}

.liste-filter select {
  border: 1px solid black;
  border-radius: 10px;
  padding: 5px;
  width: 300px;
}

.liste-filter button {
  background-color: #1a73e8;
  color: white;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #1a73e8;
  margin-left: 5px;
}

.todos {
  border: 1px outset grey;
  -webkit-box-shadow: 10px 6px 12px -2px rgba(166,166,166,1);
  -moz-box-shadow: 10px 6px 12px -2px rgba(166,166,166,1);
  box-shadow: 10px 6px 12px -2px rgba(166,166,166,1);
  border-radius: 5px;
}

.add-todo-button {
  border-radius: 50%;
  height: 50px;
  width: 50px;
  background-color: #1a73e8;
  border: 1px solid #1a73e8;
  align-self: flex-end;
  margin-top: 10px;
}

.add-todo-button i {
  color: white;
  font-size: 1.5em;
}

@media screen and (min-width: 400px) and (max-width: 992px)
{
    .todo{
        display: flex;
        justify-content: flex-start;
        
    }
    .add-todo-button{
      height: 30px;
      width:  30px;
    }
 
}
</style>
