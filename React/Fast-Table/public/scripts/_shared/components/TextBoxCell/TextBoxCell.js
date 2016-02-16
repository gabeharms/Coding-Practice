class TextBoxCell extends Cell {
  render() {
    return (
      <td><input type="text" value={this.props.value} onChange={this.props.onChange} /></td>
    )
  }
}
