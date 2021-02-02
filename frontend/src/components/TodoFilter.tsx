import React from "react";
import './TodoFilter.css';

export const TodoFilter: React.FC = () => {
    return (
        <div className="TodoFilter_content">
            <button className="TodoFilter_buttonSelected">全て</button>
            <button className="TodoFilter_buttonUnselected">未完了のみ</button>
            <button className="TodoFilter_buttonUnselected">完了のみ</button>
        </div>
    );
};