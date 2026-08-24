import type { ButtonHTMLAttributes, ReactNode } from 'react'

import styles from './Button.module.scss'

type ButtonVariant = 'primary' | 'secondary' | 'danger'

interface ButtonProps
  extends ButtonHTMLAttributes<HTMLButtonElement> {
  children: ReactNode
  variant?: ButtonVariant
}

const Button = ({
  children,
  variant = 'primary',
  className = '',
  ...props
}: ButtonProps) => {
  return (
    <button
      className={`${styles.button} ${styles[variant]} ${className}`}
      {...props}
    >
      {children}
    </button>
  )
}

export default Button