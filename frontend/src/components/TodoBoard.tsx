import React, { useState } from "react";
import "./TodoBoard.css";
import { TodoFilter } from "./TodoFilter";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";

type Todo = {
    id: number
    text: string
    completed: boolean
}

export const TodoBoard: React.FC = () => {
    const [todos] = useState<Todo[]>([
        { id: 2001, text: '洗い物をする', completed: true },
        { id: 2002, text: '洗濯物を干す', completed: false },
        { id: 2003, text: '買い物へ行く', completed: false },
    ]);

  return (
    <div className="TodoBoard_content">
      <TodoForm />
      <TodoFilter />
          <TodoList todos={todos} />
    </div>
  );
};
