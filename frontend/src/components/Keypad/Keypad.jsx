import Button from '../Button/Button';
import './Keypad.css';

const Keypad = ({ onClick, disabled }) => {
    const buttons = [
        '7', '8', '9', '/',
        '4', '5', '6', '*',
        '1', '2', '3', '-',
        '0', 'C', '=', '+'
    ];

    return (
        <div className="keypad">
            {buttons.map((btn) => (
                <Button
                    key={btn}
                    value={btn}
                    onClick={onClick}
                    disabled={disabled && btn === '='}
                />
            ))}
        </div>
    );
};

export default Keypad;