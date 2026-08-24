import Button from '../Button/Button'
import styles from './ConfirmModal.module.scss'

interface ConfirmModalProps {
  isOpen: boolean
  title: string
  message: string
  onConfirm: () => void
  onCancel: () => void
  isConfirming?: boolean
}

const ConfirmModal = ({
  isOpen,
  title,
  message,
  onConfirm,
  onCancel,
  isConfirming = false,
}: ConfirmModalProps) => {
  if (!isOpen) {
    return null
  }

  return (
    <div className={styles.overlay}>
      <div
        className={styles.modal}
        role="dialog"
        aria-modal="true"
        aria-labelledby="confirm-modal-title"
      >
        <h2 id="confirm-modal-title">{title}</h2>

        <p>{message}</p>

        <div className={styles.actions}>
          <Button
            type="button"
            variant="secondary"
            onClick={onCancel}
            disabled={isConfirming}
          >
            Cancel
          </Button>

          <Button
            type="button"
            variant="danger"
            onClick={onConfirm}
            disabled={isConfirming}
          >
            {isConfirming ? 'Removing...' : 'Delete'}
          </Button>
        </div>
      </div>
    </div>
  )
}

export default ConfirmModal