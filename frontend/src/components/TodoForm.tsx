import React from 'react';
import './TodoForm.css';

export const TodoForm: React.FC = () => {
    return (
        <div className="TodoForm_content">
            <form className="TodoForm_form">
                <div className="TodoForm_input">
                    <input type="text" placeholder="タスクを入力してください" />
                </div>
                <div className="TodoForm_button">
                    <button type="button">追加</button>
                </div>
            </form>
        </div>
    );
};
