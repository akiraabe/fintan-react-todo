import React from "react";
import './TodoBoard.css';

export const TodoBoard: React.FC = () => {
    return (
        <div className="TodoBoard_content">
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
        <div className="TodoFilter_content">
          <button className="TodoFilter_buttonSelected">全て</button>
          <button className="TodoFilter_buttonUnselected">未完了のみ</button>
          <button className="TodoFilter_buttonUnselected">完了のみ</button>
        </div>
        <ul className="TodoList_list">
          <li className="TodoItem_item">
            <div className="TodoItem_todo">
              <label>
                <input type="checkbox" className="TodoItem_checkbox" checked={true} />
                <span>洗い物をする</span>
              </label>
            </div>
            <div className="TodoItem_delete">
              <button className="TodoItem_button">x</button>
            </div>
          </li>
          <li className="TodoItem_item">
            <div className="TodoItem_todo">
              <label>
                <input type="checkbox" className="TodoItem_checkbox" />
                <span>洗濯物を干す</span>
              </label>
            </div>
            <div className="TodoItem_delete">
              <button className="TodoItem_button">x</button>
            </div>
          </li>
          <li className="TodoItem_item">
            <div className="TodoItem_todo">
              <label>
                <input type="checkbox" className="TodoItem_checkbox" />
                <span>買い物へ行く</span>
              </label>
            </div>
            <div className="TodoItem_delete">
              <button className="TodoItem_button">x</button>
            </div>
          </li>
        </ul>
      </div>

    );

};