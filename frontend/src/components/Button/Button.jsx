import './Button.css';

const Button = ({ value, onClick, disabled }) => {
    const isOperator = ['+', '-', '*', '/', '='].includes(value);

    return (
        <button
            className={`button ${isOperator ? 'operator' : ''} ${value === '=' ? 'equals' : ''}`}
            onClick={() => onClick(value)}
            disabled={disabled}
        >
            {value}
        </button>
    );
};

export default Button;