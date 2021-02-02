import React from "react";
import "./TodoList.css";
import { TodoItem } from "./TodoItem";

type Todo = {
  id: number;
  text: string;
  completed: boolean;
};

type Props = {
  todos: Todo[];
};

export const TodoList: React.FC<Props> = ({ todos }) => {
  return (
    <ul className="TodoList_list">
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          id={todo.id}
          text={todo.text}
          completed={todo.completed}
        />
      ))}
    </ul>
  );
};
