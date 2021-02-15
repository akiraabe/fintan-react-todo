import React, { useEffect, useState } from "react";
import { BackendService } from "../backend/BackendService";
import "./TodoBoard.css";
import { FilterType, TodoFilter } from "./TodoFilter";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";

type Todo = {
  id: number;
  text: string;
  completed: boolean;
};

type ShowFilter = {
  [K in FilterType]: (todo: Todo) => boolean;
};

const showFilter: ShowFilter = {
  ALL: (todo) => true,
  INCOMPLETE: (todo) => !todo.completed,
  COMPLETED: (todo) => todo.completed,
};

export const TodoBoard: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [filterType, setFilterType] = useState<FilterType>("ALL");

  useEffect(() => {
    BackendService.getTodos().then((response) => setTodos(response));
  }, []);

  const addTodo = (returnedTodo: Todo) => {
    setTodos(todos.concat(returnedTodo));
  };

  const toggleTodoCompletion = (id: number) => {
    const target = todos.find((todo) => todo.id === id);
    if (!target) {
      return;
    }
    BackendService.putTodo(id, !target.completed).then((returnedTodo) =>
      setTodos(todos.map((todo) => (todo.id === id ? returnedTodo : todo)))
    );
  };

  const deleteTodo = (id: number) => {
    console.log('####deleteTodo')
    const target = todos.find((todo) => todo.id === id);
    if (!target) {
      return;
    }
    BackendService.deleteTodo(id).then(() =>
      setTodos(todos.filter((todo) => (todo.id !== id)))
    );
  };

  const showTodos = todos.filter(showFilter[filterType]);

  return (
    <div className="TodoBoard_content">
      <TodoForm addTodo={addTodo} />
      <TodoFilter filterType={filterType} setFilterType={setFilterType} />
      <TodoList
        todos={showTodos}
        toggleTodoCompletion={toggleTodoCompletion}
        deleteTodo={deleteTodo}
      />
    </div>
  );
};
