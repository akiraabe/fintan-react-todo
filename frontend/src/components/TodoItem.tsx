import React from 'react';
import './TodoItem.css';

type Props = {
    id: number,
    text: string,
    completed: boolean,
};

export const TodoItem: React.FC<Props> = ({ id, text, completed }) => {
    return (
        <li className="TodoItem_item">
            <div className="TodoItem_todo">
                <label>
                    <input type="checkbox" className="TodoItem_checkbox" checked={completed} />
                    <span>{text}</span>
                </label>
            </div>
            <div className="TodoItem_delete">
                <button className="TodoItem_button">x</button>
            </div>
        </li>
    )
}
